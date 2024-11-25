import * as React from 'react';

import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue,
} from '@repo/ui/shadcn/select';

export default function SortSelector() {
  return (
    <Select>
      <SelectTrigger
        className="
        w-1/3 border-[0] 
        text-primary text-sm font-medium
        focus:border-0 focus:ring-opacity-0 focus:ring-white focus:shadow-none
        "
      >
        <SelectValue placeholder="Sorting by" />
      </SelectTrigger>
      <SelectContent className="border-none outline-none">
        <SelectGroup>
          <SelectLabel className="text-primary">Fruits</SelectLabel>
          <SelectItem value="apple">Apple</SelectItem>
          <SelectItem value="banana">Banana</SelectItem>
          <SelectItem value="blueberry">Blueberry</SelectItem>
          <SelectItem value="grapes">Grapes</SelectItem>
          <SelectItem value="pineapple">Pineapple</SelectItem>
        </SelectGroup>
      </SelectContent>
    </Select>
  );
}
