import ProdutoCard from "../components/ProdutoCard";

const produtosMock = [
  {
    codigo: 101,
    nome: "Ar-Condicionado Split 9000 BTUs",
    descricao: "Ideal para ambientes pequenos.",
    capacidade: "9000 BTUs",
    preco: 1299.90,
    marca: "SpringAir"
  },
  {
    codigo: 102,
    nome: "Ar-Condicionado Inverter 12000 BTUs",
    descricao: "Economia e eficiência energética.",
    capacidade: "12000 BTUs",
    preco: 1899.90,
    marca: "CoolTech"
  },
  {
    codigo: 103,
    nome: "Ar-Condicionado Portátil 10000 BTUs",
    descricao: "Fácil transporte e instalação.",
    capacidade: "10000 BTUs",
    preco: 1599.90,
    marca: "PolarWind"
  }
];

export default function Produtos() {
  return (
    <div className="p-6">
      <h1 className="text-3xl font-bold mb-6 text-center">Produtos disponíveis</h1>
      <div className="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 gap-6">
        {produtosMock.map((produto) => (
          <ProdutoCard key={produto.codigo} produto={produto} />
        ))}
      </div>
    </div>
  );
}
