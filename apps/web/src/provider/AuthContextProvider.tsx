'use client';
import React from 'react';
import { SessionContext } from '@/context/SessionContext';

export function AuthContextProvider({
  isAuth,
  children,
}: {
  isAuth: boolean;
  children: React.ReactNode;
}) {
  return (
    <SessionContext.Provider value={isAuth}>{children}</SessionContext.Provider>
  );
}

export default AuthContextProvider;
