var AuthSSO = function ({ host }) {
  if (!host) {
    throw Error('need to pass in target host SSO server');
  }
  return {
    // verify JWT against auth server
    verify: async ({ token }) => {
      const res = await fetch(`${host}/auth/verify_token/${token}`);
      return await res.json();
    },
    // successUrl: URL for redirection after login
    login: ({ successUrl }) => {
      window.open(`${host}/auth/login?successUrl=${successUrl}`, '_self');
    },
    // successUrl: URL for redirection after logout
    logout: ({ successUrl }) => {
      window.open(`${host}/auth/logout?successUrl=${successUrl}`, '_self');
    },
  };
};
