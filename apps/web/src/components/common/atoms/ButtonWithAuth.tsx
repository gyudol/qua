"use client";

export default function ButtonWithAuth({
  onClick,
  children,
  ...props
}: React.ButtonHTMLAttributes<HTMLButtonElement>) {
  function handleClick(e: React.MouseEvent<HTMLButtonElement>) {
    // eslint-disable-next-line no-alert -- for test
    alert("로그인이 필요한 작업입니다.");

    onClick && onClick(e);
  }

  return (
    <button type="button" {...{ ...props, onClick: handleClick }}>
      {children}
    </button>
  );
}
