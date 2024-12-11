import React from "react";

function MobileContainer({ children }: { children: React.ReactNode }) {
  return (
    <div
      className="
      flex        flex-col 
      mx-auto     bg-white
      relative    sm:absolute
      w-full      sm:max-w-md
      h-full      sm:max-h-[90%]
      sm:rounded-xl  sm:top-[4%]
      overflow-hidden
      "
    >
      {children}
    </div>
  );
}

export default MobileContainer;
