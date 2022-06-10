import React from 'react';
import { Navigate, Outlet } from 'react-router-dom';
import { useRecoilValueLoadable } from 'recoil';
import { hasRoleSelector, Role } from '../../state/auth/auth.atom';

const RolesProtection: React.FC<{ role: Role }> = ({ role }) => {
  const hasRole = useRecoilValueLoadable(hasRoleSelector(role));

  switch (hasRole.state) {
    case 'hasError':
      return <Navigate to={'/'}></Navigate>;
    case 'loading':
      return <></>;
    case 'hasValue':
      const value = hasRole.contents;
      if (!value) {
        return <Navigate to={'/'}></Navigate>;
      } else return <Outlet />;
    default:
      return <></>;
  }
};

export default RolesProtection;
