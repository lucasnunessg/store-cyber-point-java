import React, { useState } from 'react';
import api from './FetchApi';
import Product from './interface/Product';

interface DeleteProductProps {
  product: Product;
  onDelete: (productId: number) => void;
  onCancel: () => void;
}

const DeleteProduct: React.FC<DeleteProductProps> = ({ product, onDelete, onCancel }) => {
  const [isLoading, setIsLoading] = useState(false); // Estado para loading.

  const handleDelete = async () => {
    try {
      setIsLoading(true); 
      const token = localStorage.getItem('token');
      if (!token) {
        console.error('Usuário não está logado e/ou não é admin!');
        return;
      }
      await api.delete(`/products/${product.id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      window.location.reload();
      onDelete(product.id); 
    } catch (error) {
      console.error('Erro ao deletar produto', error);
    } finally {
      setIsLoading(false); 
    }
  };

  return (
    <div className="delete-product">
      <p>Olá, administrador, para excluir o produto, clique no botão abaixo:</p>
      <div className="delete-buttons">
        <button onClick={handleDelete} disabled={isLoading}>
          {isLoading ? 'Excluindo...' : 'Excluir'}
        </button>
        <button onClick={onCancel} disabled={isLoading}>
          Cancelar
        </button>
      </div>
    </div>
  );
};

export default DeleteProduct;
