import React, { useState, useEffect } from "react";
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "@repo/ui/shadcn/select";
import type { Category } from "@/types/admin";
import { getAllCategories } from "@/actions/admin-service";

export default function CategorySelector({
  changeHandler,
  id,
  name,
  value,
}: {
  changeHandler?: (value: string) => void; // 외부로 선택 값을 전달
  id?: string;
  name?: string;
  value?: string;
}) {
  const [category, setCategory] = useState<Category[]>([]);
  const [selectedValue, setSelectedValue] = useState<string>(value || "");

  useEffect(() => {
    const fetchCategory = async () => {
      const categoryData = await getAllCategories();
      setCategory(categoryData);
    };
    void fetchCategory();
  }, []);

  const handleValueChange = (selected: string) => {
    setSelectedValue(selected); // 내부 상태 업데이트
    if (changeHandler) {
      changeHandler(selected); // 외부 핸들러 호출
    }
  };

  return (
    <Select value={selectedValue} onValueChange={handleValueChange}>
      <SelectTrigger
        className="
        qua-span
        placeholder:text-xs placeholder:text-primary
        py-4
        w-full border-[1px] border-primary 
        text-primary text-xs font-medium
        focus:border-2 focus:ring-opacity-1 focus:ring-primary focus:shadow-none
        "
        style={{ borderRadius: "0.3rem", fontSize: "0.875rem" }}
        id={id}
        name={name}
      >
        <SelectValue placeholder="카테고리를 선택하세요" />
      </SelectTrigger>
      <SelectContent className="border-[1px] border-primary outline-none rounded-lg p-0 m-0">
        <SelectGroup className="border-none outline-none p-0 ring-0">
          {category.map((item: Category) => (
            <SelectItem
              key={item.categoryId}
              value={item.categoryName}
              className="text-xs bg-white text-primary hover:!bg-primary/60 hover:rounded-lg transition-all"
              style={{ borderRadius: "0.3rem" }}
            >
              {item.categoryName}
            </SelectItem>
          ))}
        </SelectGroup>
      </SelectContent>
    </Select>
  );
}
