package com.casaclima.backend.controller;

import com.casaclima.backend.dao.FuncionarioDAO;
import com.casaclima.backend.model.Funcionario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins = "*")
public class FuncionarioController {

    private final FuncionarioDAO dao;

    public FuncionarioController(FuncionarioDAO dao) {
        this.dao = dao;
    }

    @GetMapping
    public List<Funcionario> listar() {
        return dao.listar();
    }

    @GetMapping("/{matricula}")
        public ResponseEntity<Funcionario> buscar(@PathVariable("matricula") String matricula) {
            Funcionario funcionario = dao.buscarPorMatricula(matricula);
            if(funcionario == null)
                return ResponseEntity.notFound().build();
            return ResponseEntity.ok(funcionario);
        }

    @PostMapping("/login-gerente")
    public ResponseEntity<String> loginGerente(@RequestBody Map<String, String> loginData) {
        String matricula = loginData.get("matricula");
        String cpf = loginData.get("cpf");
        String senha = loginData.get("senha");

        Funcionario funcionario = dao.buscarPorMatricula(matricula);

        if (funcionario == null || !funcionario.getCpf().equals(cpf) || !funcionario.getSenha().equals(senha)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Matrícula, CPF ou senha incorretos");
        }
        if (funcionario.getSupervisor() != null && !funcionario.getSupervisor().trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Acesso permitido apenas para gerente/supervisor!");
        }

        return ResponseEntity.ok().build();
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrarFuncionario(@RequestBody Funcionario funcionario) {
        try {
            dao.inserir(funcionario);
            return ResponseEntity.ok("Funcionário cadastrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao cadastrar funcionário: " + e.getMessage());
        }
    }

    @PutMapping("/{matricula}/senha")
    public ResponseEntity<String> alterarSenha(
        @PathVariable("matricula") String matricula,
        @RequestBody Map<String, String> payload) 
    {
        String novaSenha = payload.get("senha");
        if (novaSenha == null || novaSenha.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Senha não pode ser vazia.");
        }
        try {
            dao.atualizarSenha(matricula, novaSenha);
            return ResponseEntity.ok("Senha alterada com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao alterar senha: " + e.getMessage());
        }
    }

    @PutMapping("/{matricula}")
    public ResponseEntity<String> atualizarFuncionario(@PathVariable String matricula, @RequestBody Funcionario funcionario) {
        try {
            dao.atualizar(matricula, funcionario);
            return ResponseEntity.ok("Funcionário atualizado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar funcionário: " + e.getMessage());
        }
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity<String> excluirFuncionario(@PathVariable("matricula") String matricula) {
        try {
            dao.excluir(matricula);
            return ResponseEntity.ok("Funcionário removido com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao remover funcionário: " + e.getMessage());
        }
    }
}