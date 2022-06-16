import axios from 'axios';
import { AUTH_SERVER_URL } from '../../constants';

const authAxios = axios.create({
  baseURL: AUTH_SERVER_URL,
});

export default authAxios;
