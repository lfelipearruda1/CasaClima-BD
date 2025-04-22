import { useState } from "react";
import Login from "./pages/Login";
import Produtos from "./pages/Produtos";

export default function App() {
  const [clienteId, setClienteId] = useState<number | null>(null);

  if (!clienteId) {
    return <Login onLogin={(id) => setClienteId(id)} />;
  }

  return <Produtos />;
}
