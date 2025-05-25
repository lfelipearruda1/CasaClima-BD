package com.casaclima.backend.controller;

import com.casaclima.backend.dao.ProdutoDAO;
import com.casaclima.backend.dto.ProdutoDTO;
import com.casaclima.backend.model.Produto;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoDAO dao;

    public ProdutoController(ProdutoDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Produto> listarProdutos() {
        return dao.listarTodos();
    }

    @GetMapping("/ativos")
    public List<Produto> listarProdutosAtivos() {
        return dao.listarAtivos();
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> buscarPorCodigo(@PathVariable("codigo") int codigo) {
        try {
            Produto produto = dao.buscarPorCodigo(codigo);
            return ResponseEntity.ok(produto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/destaques")
    public List<Produto> listarProdutosDestaques() {
        return dao.listarTopDescontos();
    }

    @PostMapping
    public ResponseEntity<Boolean> inserirProduto(@RequestBody Produto produto) {
        if (produto.getDesconto() < 0 || produto.getDesconto() > 100) {
            return ResponseEntity.badRequest().body(false);
        }

        produto.setPrecoOriginal(produto.getPreco());

        if (produto.getCaminhoImagem() == null || produto.getCaminhoImagem().isEmpty()) {
            String marca = produto.getMarca().toLowerCase().replaceAll("[^a-zA-Z0-9]", "_");
            switch (marca) {
                case "lg":
                case "hitachi":
                case "samsung":
                    produto.setCaminhoImagem("/imagens/" + marca + ".jpg");
                    break;
                default:
                    produto.setCaminhoImagem("/imagens/default.jpg");
            }
        }

        boolean inserido = dao.inserir(produto);
        if (inserido) return ResponseEntity.ok(true);
        else return ResponseEntity.status(500).body(false);
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Boolean> atualizarProduto(@PathVariable("codigo") int codigo, @RequestBody Produto produto) {
        if (produto.getDesconto() < 0 || produto.getDesconto() > 100) {
            return ResponseEntity.badRequest().body(false);
        }

        produto.setCodigo(codigo);
        if (produto.getAtivo() == null) {
            produto.setAtivo(true);
        }

        boolean atualizado = dao.atualizar(produto);
        if (atualizado) return ResponseEntity.ok(true);
        else return ResponseEntity.status(500).body(false);
    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Boolean> deletarProduto(@PathVariable("codigo") int codigo) {
        boolean deletado = dao.deletar(codigo);
        if (deletado) return ResponseEntity.ok(true);
        else return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{codigo}/desativar")
    public ResponseEntity<Void> desativarProduto(@PathVariable("codigo") int codigo) {
        boolean sucesso = dao.desativar(codigo);
        if (sucesso) return ResponseEntity.ok().build();
        else return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{codigo}/ativar")
    public ResponseEntity<Void> ativarProduto(@PathVariable("codigo") int codigo) {
        boolean sucesso = dao.ativar(codigo);
        if (sucesso) return ResponseEntity.ok().build();
        else return ResponseEntity.notFound().build();
    }

    @PostMapping("/{codigo}/uploadFoto")
    public ResponseEntity<String> uploadFoto(@PathVariable("codigo") int codigo, @RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest().body("Arquivo vazio");
            }
            String contentType = file.getContentType();
            if (contentType == null || !contentType.startsWith("image/")) {
                return ResponseEntity.badRequest().body("Arquivo não é uma imagem");
            }

            Produto produto = dao.buscarPorCodigo(codigo);
            if (produto == null) {
                return ResponseEntity.notFound().build();
            }

            Path pastaImagens = Paths.get("imagens");
            if (!Files.exists(pastaImagens)) {
                Files.createDirectories(pastaImagens);
            }

            String nomeOriginal = file.getOriginalFilename();
            if (nomeOriginal == null) {
                nomeOriginal = "imagem";
            }
            nomeOriginal = nomeOriginal.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");

            String nomeArquivo = codigo + "_" + System.currentTimeMillis() + "_" + nomeOriginal;
            Path caminhoArquivo = pastaImagens.resolve(nomeArquivo);

            Files.copy(file.getInputStream(), caminhoArquivo, StandardCopyOption.REPLACE_EXISTING);

            produto.setCaminhoImagem("/imagens/" + nomeArquivo);
            dao.atualizar(produto);

            return ResponseEntity.ok("Imagem salva com sucesso");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao salvar imagem");
        }
    }

    @GetMapping("/dashboard/maisVendidos")
    public ResponseEntity<List<ProdutoDTO>> getProdutosMaisVendidos() {
        try {
            List<ProdutoDTO> produtosMaisVendidos = dao.findMaisVendidos();
            return ResponseEntity.ok(produtosMaisVendidos);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    } 
}