export interface Task {
  id?: number;
  number?: number;
  name?:string;
  description?: string;
  matchStrings?: string[];
  excludedString?: string[];
  requiredSubStrings?: string[];
  excludedAnswers?: string[];
  likes?: any;
  comments?: string;
  regExpLevel?: any;
  author?: any;
  users?: any;
  answers?: any;
  usefulLinks?: any;
  new?:any;
}
