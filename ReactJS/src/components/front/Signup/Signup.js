import React,{useState} from 'react'
import { Link } from 'react-router-dom';
import BookService from '../../../service/BookService';
import "./Signup.css";
const Signup = () => {

  const [firstName, setFirstName] = useState('')
  const [lastName, setLastName] = useState('')
  const [kyc, setKyc] = useState('')
  const [dob, setDob] = useState('')
  const [username, setUsername] = useState('')
  const [password, setPassword] = useState('')
  const [emailId, setemailId] = useState('')

  const [role, setRole] = useState('user')
  const [verify, setVerify] = useState(false)

const signUp = (e) =>{
    e.preventDefault();
    const user = {firstName,lastName,kyc,dob,username,password,emailId,role,verify};
    console.log(user);

    BookService.addUser(user).then(((response)=>{
        console.log("User Registered.");
    })).catch(error=>{
      console.log(error);
    });
}

  return (
    <div className='signup'>
      <div className='container' >
        <div className='row col-md-10' >
          <div >
            <h5 >SignUp</h5>
            <div >
              <form>
                <div className='form-group mb-2' >
                                <label className='form-label'> First Name : </label>
                                <input type="text" placeholder="Enter the First Name" name="firstName" className='form-control'
                                    value={firstName}
                                    onChange= {(e) => setFirstName(e.target.value)}
                                ></input>
                </div>
                <div className='form-group mb-4'  >
                                <label className='form-label' > Last Name : </label>
                                <input type="text" placeholder="Enter the Last Name" name="lastName" className='form-control'
                                    value={lastName}
                                    onChange= {(e) => setLastName(e.target.value)}
                                ></input>
                </div>
                <div className='form-group mb-4' >
                                <label className='form-label' > KYC : </label>
                                <input type="text" placeholder="Enter the KYC No." name="kyc" className='form-control'
                                    value={kyc}
                                    onChange= {(e) => setKyc(e.target.value)}
                                ></input>
                </div>
                <div className='form-group mb-4' >
                                <label className='form-label' > Enter the Date of Birth : </label>
                                <input type="text" placeholder="Enter the Date of Birth (YYYY-MM-DD)." name="dob" className='form-control'
                                    value={dob}
                                    onChange= {(e) => setDob(e.target.value)}
                                ></input>
                </div>
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
                <div className='form-group mb-4' >
                                <label className='form-label' > Email ID : </label>
                                <input type="text" placeholder="Enter the EMail ID" name="password" className='form-control'
                                    value={emailId}
                                    onChange= {(e) => setemailId(e.target.value)}
                                ></input>
                </div>
                <button className='btn btn-success' onClick={(e) => signUp(e)}> SUBMIT </button>

                            <Link to="/getAllBooks" className="btn btn-danger "> CANCEL </Link>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div>
    
  )
}

export default Signup