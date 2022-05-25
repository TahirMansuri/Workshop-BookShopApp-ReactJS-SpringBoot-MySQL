import axios from "axios";

const BASE_API_URL_BOOK = "http://localhost:8080/bookstoreapp/api/book";
const BASE_API_URL_USER = "http://localhost:8080/bookstoreapp/api/user";
const BASE_API_URL_CART = "http://localhost:8080/bookstoreapp/api/cart";
//const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYW5kZXNoMTIzIiwiYXV0aCI6InVzZXIiLCJpYXQiOjE2NTMyMzQ0NzEsImV4cCI6MTY1MzMyMDg3MX0.1LfB8U_yv6vg907HnKYSHRRMkNnRAzpkQzR68pNJJMQ";
//let headers = {
//    'token': 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTYW5kZXNoMTIzIiwiYXV0aCI6InVzZXIiLCJpYXQiOjE2NTMyMzQ0NzEsImV4cCI6MTY1MzMyMDg3MX0.1LfB8U_yv6vg907HnKYSHRRMkNnRAzpkQzR68pNJJMQ'
//}

class BookService {

    getAllBooks() {
        return axios.get(BASE_API_URL_BOOK + "/get");
    }

    addUser(user) {
        return axios.post(BASE_API_URL_USER + "/add", user);
    }

    login(login){
        return axios.post(BASE_API_URL_USER + "/login", login);
    }

    addCart(token,bookId) {
        return axios.post(BASE_API_URL_CART + `/cart?serialNumber=${bookId}`,{},{headers: {'token' :  token }});
    }

    getCart(token){
        return axios.get(BASE_API_URL_CART + "/cart",{headers: {'token' :  token }});
    }
}
export default new BookService();