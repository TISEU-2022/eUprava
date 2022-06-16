import axios from 'axios';
import { getRecoil } from 'recoil-nexus';
import { MATICAR_BACKEND_URL } from '../constants';
import { tokenAtom } from '../state/auth/auth.atom';

export const apiAxios = axios.create({
  baseURL: MATICAR_BACKEND_URL,
});

apiAxios.interceptors.request.use(
  (config) => {
    const token = getRecoil(tokenAtom);
    if (token) {
      return {
        ...config,
        headers: {
          ...config.headers,
          Authorization: `Bearer ${token}`,
        },
      };
    }
    return config;
  },
  (error) => Promise.reject(error),
);
