interface Produto {
    codigo: number;
    nome: string;
    descricao: string;
    capacidade: string;
    preco: number;
    marca: string;
  }
  
  export default function ProdutoCard({ produto }: { produto: Produto }) {
    return (
      <div className="border rounded-lg shadow p-4 bg-white">
        <h2 className="text-xl font-semibold text-gray-800">{produto.nome}</h2>
        <p className="text-gray-600 text-sm">{produto.descricao}</p>
        <p className="text-sm">BTUs: {produto.capacidade}</p>
        <p className="font-bold text-green-600">R$ {produto.preco.toFixed(2)}</p>
        <p className="text-sm text-gray-500">Marca: {produto.marca}</p>
      </div>
    );
  }
  