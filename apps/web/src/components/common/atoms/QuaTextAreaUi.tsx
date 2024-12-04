import * as React from 'react';
import { cn } from '@repo/ui/lib/utils';

export type InputProps = React.InputHTMLAttributes<HTMLTextAreaElement> & {
  rows?: number;
};

const QuaTextAreaUI = React.forwardRef<HTMLTextAreaElement, InputProps>(
  ({ className, rows, ...props }, ref) => {
    return (
      <textarea
        rows={rows}
        className={cn(
          'w-full py-[0.5rem] transition duration-300 rounded-[0.3rem] outline-none bg-white px-2 ring-1 ring-primary',
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
QuaTextAreaUI.displayName = 'Input';

export { QuaTextAreaUI };
