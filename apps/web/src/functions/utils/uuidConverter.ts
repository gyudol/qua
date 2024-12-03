import { v4 as uuidv4 } from 'uuid';

export const UuidConverter = () => {
  return uuidv4().toString();
};
