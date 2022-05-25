import React,{useState, useEffect} from 'react'
import BookService from '../../../service/BookService'
import { ToastContainer, toast, Zoom, Bounce } from 'react-toastify';
import "react-toastify/dist/ReactToastify.css";
import "./Cart.css";
const Cart = () => {

  const [cartItems, setCartItems] = useState([])
  let totalPrice = cartItems.reduce((price, item) => price + item.purchaseQuantity * item.price, 0);
  useEffect(() => {
    getCart();
    
  }, [])
  
  const getCart = () => {

          let data;
          let token;
          if(localStorage.getItem("UserData") == null) {
              toast.error("Please Login First. Then Add Books to Cart.");
          } else {
              data = localStorage.getItem("UserData");
              token = JSON.parse(data);
              console.log(token.loginInfo.token);
              if(token.loginInfo.token === ' ') {
                  toast.error("Please Login First. Then Add Books to Cart.");
              } else {
                  //toast.success("Book Added to Cart");
                  BookService.getCart(token.loginInfo.token).then((response)=>{
                    setCartItems(response.data.object);
                    console.log(cartItems);
                }).catch(error=>{
                        console.log(error);
                });
              }
          }

     // BookService.getCart().then().catch();
  }

  var [qty, setQty] = useState(1)
  
  const addBookQty = (index,item) => {
        const newCart = [...cartItems];
        item.purchaseQuantity++;
        newCart[index] = item;
        setCartItems(newCart);
        console.log(cartItems);
  }
  return (
    // <div className='products'>
    //     {cartItems.map((booksItem) => (

    //         <div className='card' >
    //             <div>
    //                 <img className='product-image' src={booksItem.logo} alt={booksItem.name}></img>
    //             </div>
    //             <div><h5 className='product.name'>{booksItem.name}</h5></div>
    //             <div className='product-desc'>{booksItem.description}</div>
    //             <div className='product-price'>Rs. {booksItem.price}</div>
    //             {/* <div><button className='product-add-button' onClick={() => handleAddProduct(productItem)}>Add to Cart</button></div> */}
    //             <div><button className='product-add-button' >Add to Cart</button></div>
    //         </div>
    //     ))}
    // </div>
    <div className='cart-items'>
        <div className='cart-items-header'>Cart Items</div>
        {cartItems.length === 0 && (
            <div className='cart-items-empty'>No Items are Added.</div>
        )}
        <div>
            {
                
                cartItems.map((item) => (
                    <div key={item.bookId} className='cart-items-list'>
                        <img className='cart-items-images' src={item.logo} alt={item.name} />
                        <div className='cart-items-name'>{item.name}</div>
                        <div className='cart-items-function'>
                            <button className='cart-items-add' >+</button>
                            <button className='cart-items-remove'>-</button>
                        </div>
                        <div className='cart-items-price'> {item.price} * {item.purchaseQuantity} </div>
                        
                    </div>
                    
                ))
            }
        </div>
        <div className='cart-items-total-price-name'>
                            Total Price
                            <div className='cart-items-total-price'> Rs. {totalPrice} </div>
                        </div>
    </div>
  )
}

export default Cart