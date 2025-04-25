import { useState } from "react";

export default function Login({ onLogin }: { onLogin: (id: number) => void }) {
  const [cpfOuCnpj, setCpfOuCnpj] = useState("");
  const [nome, setNome] = useState("");

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onLogin(1001);
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gray-100">
      <form
        onSubmit={handleSubmit}
        className="bg-white p-6 rounded shadow w-full max-w-md"
      >
        <h2 className="text-2xl font-bold mb-4 text-center">Login do Cliente</h2>
        <input
          className="w-full p-2 border mb-4 rounded"
          type="text"
          placeholder="Nome"
          value={nome}
          onChange={(e) => setNome(e.target.value)}
        />
        <input
          className="w-full p-2 border mb-4 rounded"
          type="text"
          placeholder="CPF ou CNPJ"
          value={cpfOuCnpj}
          onChange={(e) => setCpfOuCnpj(e.target.value)}
        />
        <button
          type="submit"
          className="w-full bg-blue-600 text-white p-2 rounded hover:bg-blue-700"
        >
          Entrar
        </button>
      </form>
    </div>
  );
}
