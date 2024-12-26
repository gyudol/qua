import * as React from 'react';
import { cn } from '@repo/ui/lib/utils';

export type InputProps = React.InputHTMLAttributes<HTMLInputElement>;

const QuaInputUI = React.forwardRef<HTMLInputElement, InputProps>(
  ({ className, type, ...props }, ref) => {
    return (
      <input
        type={type}
        className={cn(
          'w-full h-[2.5rem] transition duration-300 rounded-[0.3rem] outline-none bg-white px-2 ring-1 ring-primary',
          'placeholder:text-xs placeholder:text-primary text-sm',
          'focus:bg-primary focus:ring-0 focus:ring-primary focus:ring-opacity-50 focus:text-white',
          className
        )}
        ref={ref}
        {...props}
      />
    );
  }
);
QuaInputUI.displayName = 'Input';

export { QuaInputUI };
