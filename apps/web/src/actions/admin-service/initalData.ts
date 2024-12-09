'use server';

import type { CategoryListType } from '@/types/admin/admin-service';
import { getHeaders, processResponse } from '../common/index';

const API_SERVER = process.env.BASE_API_URL;
const PREFIX = 'admin-service';

export async function getCategoryData(): Promise<CategoryListType[]> {
  // console.log('getCategoryData');
  const URI = `${API_SERVER}/${PREFIX}/v1/category`;

  const res: Response = await fetch(URI, {
    headers: await getHeaders(),
    method: 'GET',
    next: { revalidate: 3600 },
  });

  return processResponse<CategoryListType[], false>({ res });
}
