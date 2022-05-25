import axios from 'axios';
import React,{useState,useEffect, useCallback} from 'react'
import BookService from '../../../service/BookService';
import data from '../../back/data/data';
import "./Products.css"
import { ToastContainer, toast, Zoom, Bounce } from 'react-toastify';
import "react-toastify/dist/ReactToastify.css";
const Products = () => {

    const [books, setBooks] = useState([])

    useEffect(() => {
      getAllBooks();
    }, [])

    const getAllBooks = () => {
        BookService.getAllBooks().then((response)=>{
            setBooks(response.data.object);
            console.log(response.data.object);
        }).catch(error=>{
            console.log(error);
        });
    }

    const addCart = (e, booksItem) => {
        let data;
        let token;
        e.preventDefault();
        console.log(booksItem);
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
                addBookToDBCart(token.loginInfo.token,booksItem.serialNo);
            }
        }
    }

    const addBookToDBCart = (token,serialNo) => {
        BookService.addCart(token,serialNo).then((response)=>{
            if(response.data.statusCode === 200) {
                toast.success("Book Added to Cart in DB")
            } else {
                toast.error("Book Removed From Cart in DB");
            }
        }).catch(error=>{
                console.log(error);
        });
    }


    //console.log(booksItems);
  return (
    <div className='products'>
        {books.map((booksItem) => (

            <div className='card' >
                <div>
                    <img className='product-image' src={booksItem.logo} alt={booksItem.name}></img>
                </div>
                <div><h5 className='product.name'>{booksItem.name}</h5></div>
                <div className='product-desc'>{booksItem.description}</div>
                <div className='product-price'>Rs. {booksItem.price}</div>
                {/* <div><button className='product-add-button' onClick={() => handleAddProduct(productItem)}>Add to Cart</button></div> */}
                <div><button className='product-add-button' onClick={(e) => addCart(e,booksItem)}>Add to Cart</button></div>
            </div>
        ))}
    </div>
  );
};

export default Products