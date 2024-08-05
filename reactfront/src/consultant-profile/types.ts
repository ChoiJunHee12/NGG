// src/types.ts
export interface TabProps {
  active?: boolean;
}

export interface ProfileData {
  id: string;
  name: string;
  gender: string;
  title: string;
  phone: string;
  email: string;
  birthYear: string;
  area: string;
  expertise: string;
  experience: string;
  clients: string;
  introduce: string;
}


export interface Education {
  id: string;
  degree: string;
  field: string; // field 프로퍼티 추가
  school: string;
  year: string;
}

export interface Expertise {
  id: string;
  skill: string;
}

export interface Career {
  id: string;
  company: string;
  position: string;
  period: string; 
  description: string;
}