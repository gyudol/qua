import React from 'react';

function InnerMobileContainer({ children }: { children: React.ReactNode }) {
  return (
    <div className="relative w-full md:max-w-md mx-auto md:h-[90%] md:absolute md:top-10 rounded-b-xl md:rounded-xl bg-white shadow-md overflow-hidden flex flex-col">
      <div className="md:flex-1 md:overflow-auto overflow-visible">
        {children}
      </div>
    </div>
  );
}

export default InnerMobileContainer;
