export interface Grade {
  id: number;
  name: string;
  pointThreshold: number;
  imageUrl: string;
}

export interface GetGradeReq {
  gradeId: number;
}
