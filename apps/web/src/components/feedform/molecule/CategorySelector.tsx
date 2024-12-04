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

export default function CategorySelector({
  changeHandler,
  id,
  name,
  value,
}: {
  changeHandler?: () => void;
  id?: string;
  name?: string;
  value?: string;
}) {
  return (
    <Select>
      <SelectTrigger
        className="
        w-full border-[0] 
        text-primary text-sm font-medium
        focus:border-0 focus:ring-opacity-0 focus:ring-white focus:shadow-none
        "
        onChange={changeHandler}
        id={id}
        name={name}
        value={value}
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
