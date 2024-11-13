import { Routes, Route, BrowserRouter } from 'react-router-dom';

import './App.css';
import Products from './Products.tsx';
import Footer from './Footer.tsx';
import Header from './Header.tsx';
import Persons from './Persons.tsx';

function App() {
  return (
    <BrowserRouter>
      <Header />
      <Routes>
        <Route path="/products" element={<Products />} />
        <Route path="/persons" element={<Persons />} />
      </Routes>
      <Footer />
      </BrowserRouter>

  );
}

export default App;
