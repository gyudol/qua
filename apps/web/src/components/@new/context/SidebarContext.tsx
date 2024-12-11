"use client";
import React, { createContext, useContext, useState } from "react";

interface SidebarContextType {
  isOpen: boolean;
  setIsOpen: React.Dispatch<React.SetStateAction<boolean>>;
}

export const SidebarContext = createContext<SidebarContextType>(
  {} as SidebarContextType,
);
export const useSidebarContext = () => useContext(SidebarContext);

export function SidebarContextProvider({
  children,
}: {
  children: React.ReactNode;
}) {
  const [isOpen, setIsOpen] = useState(false);
  const value = { isOpen, setIsOpen };
  return (
    <SidebarContext.Provider {...{ value }}>{children}</SidebarContext.Provider>
  );
}
