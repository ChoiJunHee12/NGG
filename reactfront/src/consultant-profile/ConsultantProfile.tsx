import React, { useState } from 'react';
import {
    Container, Header, ProfileImage, HeaderInfo, Name, Gender, Title,
    TabContainer, Tab, Section, SectionTitle, InfoGrid, InfoItem,
    InfoIcon, InfoText, EducationItem, Year,
    Details, School, Degree, Introduce, EditButton,
    Modal, ModalContent, ModalHeader, ModalBody, ModalFooter,
    Form, FormGroup, Label, Input, Button, CancelButton
} from './ConsultantProfile.styles';
import { ProfileData, Education, Career } from './types';

const ConsultantProfile: React.FC = () => {
    const [activeTab, setActiveTab] = useState('info');
    const [isModalOpen, setIsModalOpen] = useState(false);

    const profileData: ProfileData = {
        id: 1,
        name: '김지원',
        gender: '여',
        title: '경영/컨설팅 컨설턴트',
        phone: '010-1234-5678',
        email: 'jiwon.kim@consultancy.com',
        birthYear: '1992.07.07',
        area: '서울',
        expertise: '경영/컨설팅', 
        experience: '5-10년',
        clients: '100회 이상',
        introduce: '글로벌 시장에서의 성공을 위한 맞춤형 전략을 제공합니다. 언어와 문화의 장벽을 넘어 여러분의 비즈니스가 세계로 나아갈 수 있도록 돕겠습니다.'
    };

    const [editedProfile, setEditedProfile] = useState<ProfileData>({ ...profileData });

    const educationData: Education[] = [
        { id: 1, profileId: 1, degree: '석사', field: '국제경영학', school: 'University of Canberra', year: '2018' },
        { id: 2, profileId: 1, degree: '학사', field: '영어영문학', school: '서울대학교', year: '2015' }
    ];

    const careerData: Career[] = [
        { id: 1, profileId: 1, company: 'ABC 컨설팅', position: '선임 컨설턴트', period: '2018-2023', description: '글로벌 기업 대상 비즈니스 전략 수립' },
        { id: 2, profileId: 1, company: 'XYZ 교육', position: '영어 강사', period: '2015-2018', description: '기업 임직원 대상 비즈니스 영어 교육' }
    ];

    const handleEditClick = () => {
        setIsModalOpen(true);
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
    };

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setEditedProfile(prev => ({ ...prev, [name]: value }));
    };

    const handleSubmit = (e: React.FormEvent) => {
        e.preventDefault();
        // 프로필 업데이트 로직
        console.log('Updated profile:', editedProfile);
        setIsModalOpen(false);
    };

    return (
        <Container>
            <Header>
                <ProfileImage src="/img/consult_profile_img/consultant.jpg" alt={profileData.name} />
                <HeaderInfo>
                    <div>
                        <Name as="h1">{profileData.name} <Gender>({profileData.gender})</Gender></Name>
                        <Title as="h2">{profileData.title}</Title>
                    </div>
                    <EditButton onClick={handleEditClick}>프로필 수정</EditButton>
                </HeaderInfo>
            </Header>

            <TabContainer>
                <Tab active={activeTab === 'info'} onClick={() => setActiveTab('info')}>기본 정보</Tab>
                <Tab active={activeTab === 'education'} onClick={() => setActiveTab('education')}>학력 및 경력</Tab>
            </TabContainer>

            {activeTab === 'info' && (
                <>
                    <Section>
                        <SectionTitle as="h3">기본 정보</SectionTitle>
                        <InfoGrid>
                            <InfoItem>
                                <InfoIcon>📞</InfoIcon>
                                <InfoText>{profileData.phone}</InfoText>
                            </InfoItem>
                            <InfoItem>
                                <InfoIcon>✉️</InfoIcon>
                                <InfoText>{profileData.email}</InfoText>
                            </InfoItem>
                            <InfoItem>
                                <InfoIcon>🎂</InfoIcon>
                                <InfoText>{profileData.birthYear}</InfoText>
                            </InfoItem>
                            <InfoItem>
                                <InfoIcon>📍</InfoIcon>
                                <InfoText>{profileData.area}</InfoText>
                            </InfoItem>
                        </InfoGrid>
                    </Section>
                    <Section>
                        <SectionTitle as="h3">주요 경력</SectionTitle>
                        <InfoGrid>
                            <InfoItem>
                                <InfoIcon>💼</InfoIcon>
                                <InfoText>컨설팅 경력: {profileData.experience}</InfoText>
                            </InfoItem>
                            <InfoItem>
                                <InfoIcon>👥</InfoIcon>
                                <InfoText>프로젝트 수행: {profileData.clients}</InfoText>
                            </InfoItem>
                        </InfoGrid>
                    </Section>
                    <Section>
                        <SectionTitle as="h3">자기 소개</SectionTitle>
                        <Introduce>{profileData.introduce}</Introduce>
                    </Section>
                </>
            )}

            {activeTab === 'education' && (
                <>
                    <Section>
                        <SectionTitle as="h3">학력</SectionTitle>
                        {educationData.map((edu, index) => (
                            <EducationItem key={index}>
                                <Year>{edu.year}</Year>
                                <Details>
                                    <School>{edu.school}</School>
                                    <Degree>{edu.degree} in {edu.field}</Degree>
                                </Details>
                            </EducationItem>
                        ))}
                    </Section>
                    <Section>
                        <SectionTitle as="h3">상세 경력</SectionTitle>
                        {careerData.map((career, index) => (
                            <EducationItem key={index}>
                                <Year>{career.period}</Year>
                                <Details>
                                    <School>{career.company}</School>
                                    <Degree>{career.position}</Degree>
                                    <p>{career.description}</p>
                                </Details>
                            </EducationItem>
                        ))}
                    </Section>
                </>
            )}

            {/* 프로필 수정 모달 */}
            <Modal isOpen={isModalOpen}>
                <ModalContent>
                    <ModalHeader>
                        <h2>프로필 수정</h2>
                        <button onClick={handleCloseModal}>&times;</button>
                    </ModalHeader>
                    <ModalBody>
                        <Form onSubmit={handleSubmit}>
                            <FormGroup>
                                <Label htmlFor="name">🍀 이름</Label>
                                <Input
                                    type="text"
                                    id="name"
                                    name="name"
                                    value={editedProfile.name}
                                    onChange={handleInputChange}
                                    disabled
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="title">👔 직책</Label>
                                <Input
                                    type="text"
                                    id="title"
                                    name="title"
                                    value={editedProfile.title}
                                    onChange={handleInputChange}
                                    disabled
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="phone">📞 전화번호</Label>
                                <Input
                                    type="tel"
                                    id="phone"
                                    name="phone"
                                    value={editedProfile.phone}
                                    onChange={handleInputChange}
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="email">✉️ 이메일</Label>
                                <Input
                                    type="email"
                                    id="email"
                                    name="email"
                                    value={editedProfile.email}
                                    onChange={handleInputChange}
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="birthYear">🎂 생년월일</Label>
                                <Input
                                    type="text"
                                    id="birthYear"
                                    name="birthYear"
                                    value={editedProfile.birthYear}
                                    onChange={handleInputChange}
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="area">📍 활동 지역</Label>
                                <Input
                                    type="text"
                                    name="area"
                                    id="area"
                                    value={editedProfile.area}
                                    onChange={handleInputChange}
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="expertise">🌟 전문 분야</Label>
                                <Input
                                    type="expertise"
                                    id="expertise"
                                    name="expertise"
                                    value={editedProfile.expertise}
                                    onChange={handleInputChange}
                                    disabled
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="experience">💼 컨설팅 경력</Label>
                                <Input
                                    type="experience"
                                    id="experience"
                                    name="experience"
                                    value={editedProfile.experience}
                                    onChange={handleInputChange}
                                    disabled
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="clients">👥 프로젝트 수행</Label>
                                <Input
                                    type="clients"
                                    id="clients"
                                    name="clients"
                                    value={editedProfile.clients}
                                    onChange={handleInputChange}
                                    disabled
                                />                                
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="introduce">✏️ 자기 소개</Label>
                                <textarea
                                    id="introduce"
                                    name="introduce"
                                    value={editedProfile.introduce}
                                    onChange={handleInputChange}
                                    rows={5}
                                    style={{
                                        width: '100%',
                                        padding: '8px',
                                        borderRadius: '4px',
                                        border: '1px solid #ccc',
                                        resize: 'vertical'
                                    }}
                                />
                            </FormGroup>
                            <ModalFooter>
                                <Button type="submit">저장</Button>
                                <CancelButton type="button" onClick={handleCloseModal}>취소</CancelButton>
                            </ModalFooter>
                        </Form>
                    </ModalBody>
                </ModalContent>
            </Modal>
        </Container>
    );
};

export default ConsultantProfile;
