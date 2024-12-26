"use client";

import { createContext, useContext } from "react";

export interface SessionContextType {
  isAuthenticated: boolean;
  memberUuid?: string;
  nickname?: string;
}

export const SessionContext = createContext<SessionContextType>({
  isAuthenticated: false,
});
export const useSessionContext = () => useContext(SessionContext);
