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
  answers?: Answer[];
  usefulLinks?: any;
  new?:any;
}

export interface Answer {
  id?:number;
  answer:string;
  regexpTask?:Task;
}



export interface CheckedResult {
  success: boolean;
  result: any;
}



