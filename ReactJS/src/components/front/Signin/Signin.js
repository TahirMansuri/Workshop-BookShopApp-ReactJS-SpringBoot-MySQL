import React,{useState} from 'react'
import { Link } from 'react-router-dom';
import BookService from '../../../service/BookService';
import "./Signin.css";
import 'react-notifications/lib/notifications.css';
import {NotificationContainer, NotificationManager } from 'react-notifications';
import { ToastContainer, toast, Zoom, Bounce } from 'react-toastify';
import "react-toastify/dist/ReactToastify.css";


const SignIn = () => {

    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')

    const signIn = (e) =>{
        e.preventDefault();
        const login = {username,password};
        
        BookService.login(login).then((response)=>{
            console.log(response.data);
            console.log("Login Success.");
            console.log(response.data.message);
            console.log(response.data.statusCode);
            if(response.data.statusCode === 203) {
                toast.error(response.data.message);
            } else {
                localStorage.setItem("UserData",JSON.stringify(response.data));
                toast.success(response.data.message);
            }
        }).catch(error=>{
            console.log(error);
        });
    }

  return (
    <div className='signin'>
    
      <div className='container' >
      
        <div className='row col-md-10' >
          <div >
            <h5 >SignIn</h5>
            <div >
              <form>
                <div className='form-group mb-4' >
                                <label className='form-label' > Username : </label>
                                <input type="text" placeholder="Enter the Username." name="username" className='form-control'
                                    value={username}
                                    onChange= {(e) => setUsername(e.target.value)}
                                ></input>
                </div>
                <div className='form-group mb-4' >
                                <label className='form-label' > Password : </label>
                                <input type="text" placeholder="Enter the Passwrod" name="password" className='form-control'
                                    value={password}
                                    onChange= {(e) => setPassword(e.target.value)}
                                ></input>
                </div>

                <button className='btn btn-success' onClick={(e) => signIn(e)}> LOGIN </button>
                <Link to="/" className="btn btn-danger "> CANCEL </Link>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>  
    )
}

export default SignIn