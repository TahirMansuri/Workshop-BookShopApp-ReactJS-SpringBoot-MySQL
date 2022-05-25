import React from 'react'
import { Link } from 'react-router-dom'
import "./Header.css";

const Header = () => {
  return (
      <header className='header'>
            <div>
                <h1>
                    <Link to="/" className='logo'>
                        Book Store App
                    </Link>
                </h1>
            </div>

            <div className='header-links'>
                <ul>
                    <li>
                        <Link to="/">Home</Link>
                    </li>
                </ul>
                <ul>
                    <li>
                        <Link to="/signup">SignUp</Link>
                    </li>
                </ul>
                <ul>
                    <li>
                        <Link to="/signin">SignIn</Link>
                    </li>
                </ul>
                <ul>
                    <li>
                        <Link to="/cart" className='cart'>
                        <i class="fa-solid fa-cart-shopping"></i>
                        </Link>
                    </li>
                </ul>
            </div>
      </header>
  )
}

export default Header