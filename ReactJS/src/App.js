import React,{useState} from 'react'
import data from "./components/back/data/data"
import Header from "./components/front/Header/Header"
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Signup from './components/front/Signup/Signup'
import Signin from './components/front/Signin/Signin'
import Cart from './components/front/Cart/Cart'
import Products from './components/front/Products/Products';
import { ToastContainer } from 'react-toastify';

const App = () => {

  const [cartItems, setCartItems] = useState([])

  return (
    <div>
      
      <Router>
        <Header />
        <ToastContainer />
          <div className='container'>
            <Routes>
              <Route path="/" element={<Products />}></Route>
              <Route path='/signup' element={<Signup />}></Route>
              
              <Route path='/signin' element={<Signin />}></Route>
              <Route path="/cart" element={<Cart />}></Route>
            </Routes>
          </div>
      </Router>
    </div>
  )
}

export default App