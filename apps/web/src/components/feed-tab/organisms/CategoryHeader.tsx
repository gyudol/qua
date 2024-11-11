"use client";

import { withParams } from "@/components/common/atoms";
import { CommonHeader } from "@/components/common/molecules";

const CategoryHeader = withParams(CommonHeader, "categoryName");
export default CategoryHeader;
