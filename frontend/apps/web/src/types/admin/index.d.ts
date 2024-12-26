export interface Category {
  categoryId: number;
  categoryName: string;
  viewType: string;
}

export interface CategoryReq {
  categoryId: number;
}

export type GetCategoryReq = CategoryReq;

export interface PutCategoryReq extends CategoryReq {
  categoryName: string;
  viewType: string;
}

export type DeleteCategoryReq = CategoryReq;

export interface PostCategoryReq extends CategoryReq {
  categoryName: string;
  viewType: string;
}
