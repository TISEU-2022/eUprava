import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { hasRoleSelector } from '../../state/auth/auth.atom';
import { useRecoilValueLoadableOrDefault } from '../../state/hooks.recoil';

const RoleRedirector = () => {
  const isWorker = useRecoilValueLoadableOrDefault(
    hasRoleSelector('maticar_worker'),
    false,
  );
  const isAdmin = useRecoilValueLoadableOrDefault(
    hasRoleSelector('maticar_administrator'),
    false,
  );
  const isUser = useRecoilValueLoadableOrDefault(
    hasRoleSelector('maticar_user'),
    false,
  );

  const navigate = useNavigate();

  useEffect(() => {
    if (isWorker) navigate('/worker/panel');
  }, [isWorker]);

  useEffect(() => {
    if (isAdmin) navigate('/admin/panel');
  }, [isAdmin]);

  useEffect(() => {
    if (isUser) navigate('/user/panel');
  }, [isUser]);

  return <></>;
};

export default RoleRedirector;
