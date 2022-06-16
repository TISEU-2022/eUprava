import { createStandaloneToast } from '@chakra-ui/react';
import { atom, selector, selectorFamily } from 'recoil';
import localStorageEffect from '../effects.recoil';
import authAxios from './auth.axios';

export type AuthVerifyResponse = {
  claims: {
    exp: number;
    iat: number;
    identityNumber: string;
    roles: Array<string>;
    sub: string;
    username: string;
  };
  user: UserData;
};

export type UserData = {
  firstName: string;
  lastName: string;
  roles: Array<string>;
  username: string;
  __v: number;
  _id: string;
}

const tokenAtom = atom({
  key: 'tokenAtom',
  default: '',
  effects: [localStorageEffect('token')],
});



const authVerifyResponseSelector = selector({
  key: 'authVerifyResponseSelector',
  get: async ({ get }) => {
    const token = get(tokenAtom);
    if (!token) return null;
    try {
      const response = await authAxios.get<AuthVerifyResponse>(
        `auth/verify_token/${token}`,
      );
      return response;
    } catch (e) {
      return null;
    }
  },
});

const userSelector = selector<UserData | null>({
  key: "userToken",
  get: ({ get }) => {
    const token = get(authVerifyResponseSelector);
    try {
      return token?.data?.user ?? null;
    } catch {
      return null;
    }
  },
});

const isLoggedInSelector = selector({
  key: 'isLoggedInSelector',
  get: async ({ get }) => {
    const response = get(authVerifyResponseSelector);
    if (!response) return false;
    let authenticated: boolean;
    try {
      if (response.status === 200) authenticated = true;
      else authenticated = false;
    } catch (e) {
      authenticated = false;
    }
    return authenticated;
  },
});

export type Role = 'maticar_administrator' | 'maticar_worker' | 'maticar_user';

const hasRoleSelector = selectorFamily<boolean, Role>({
  key: 'hasRoleSelector',
  get:
    (param: string) =>
      async ({ get }) => {
        const response = get(authVerifyResponseSelector);
        if (!response) return false;
        try {
          const roles = response.data.user.roles;
          if (roles.includes(param)) {
            const toast = createStandaloneToast();
            toast({
              title: `Welcome ${response.data.user.firstName} ${response.data.user.lastName}.`,
              description: `You've logged in with roles ${roles.join(', ')}`,
              status: 'success',
              duration: 2000,
              isClosable: true,
            });
            return true;
          }
          return false;
        } catch (e) {
          return false;
        }
      },
});

export { tokenAtom, isLoggedInSelector, hasRoleSelector, userSelector };
