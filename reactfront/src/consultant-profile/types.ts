// src/types.ts
export interface TabProps {
  active?: boolean;
}

export interface ProfileData {
  id: number;
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
  id: number;
  profileId: number;
  degree: string;
  field: string; // field 프로퍼티 추가
  school: string;
  year: string;
}

export interface Expertise {
  id: number;
  profileId: number;
  skill: string;
}

export interface Career {
  id: number;
  profileId: number;
  company: string;
  position: string;
  period: string; // period 프로퍼티 추가
  description: string; // description 프로퍼티 추가
}
