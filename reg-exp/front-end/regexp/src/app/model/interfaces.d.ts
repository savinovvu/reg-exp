export interface Task {
  id?: number;
  number?: number;
  name?: string;
  max: number;
  min: number;
  title?:string;
  score?:number;
  description?: string;
  specialConditions?:[];
  matchedStrings?: [];
  excludedStrings?: [];
  requiredSubStrings?: [];
  excludedAnswers?: [];
  likes?: any;
  comments?: string;
  regExpLevel?: any;
  author?: any;
  users?: any;
  answers?: Answer[];
  usefulLinks?: any;
  new?: any;
}



export interface Answer {
  id?: number;
  answer: string;
  regexpTask?: Task;
}



export interface CheckedResult {
  success: boolean;
  result: any;
}



