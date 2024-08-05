import React, { useState, useEffect } from 'react';
import {
    Container, Header, ProfileImage, HeaderInfo, Name, Gender, Title,
    TabContainer, Tab, Section, SectionTitle, InfoGrid, InfoItem,
    InfoIcon, InfoText, EducationItem, Year,
    Details, School, Degree, Introduce, EditButton,
    Modal, ModalContent, ModalHeader, ModalBody, ModalFooter,
    Form, FormGroup, Label, Input, Button, CancelButton, Textarea
} from './ConsultantProfile.styles';
import { ProfileData, Education, Career } from './types';

const ConsultantProfile: React.FC = () => {
    const [activeTab, setActiveTab] = useState('info');
    const [isModalOpen, setIsModalOpen] = useState(false);
    const [profileData, setProfileData] = useState<ProfileData | null>(null);
    const [educationData, setEducationData] = useState<Education[]>([]);
    const [careerData, setCareerData] = useState<Career[]>([]);
    const [editedProfile, setEditedProfile] = useState<ProfileData | null>(null);

    // 상태 추가: 비밀번호 및 비밀번호 확인
    const [password, setPassword] = useState('');
    const [confirmPassword, setConfirmPassword] = useState('');
    const [showConfirmPassword, setShowConfirmPassword] = useState(false);

    useEffect(() => {
        fetchProfileData();
    }, []);

    const fetchProfileData = async () => {
        try {
            const profileResponse = await fetch(`${process.env.REACT_APP_BACK_END_URL}/consultantprofile/1/profile`);
            if (!profileResponse.ok) {
                throw new Error(`HTTP error! Status: ${profileResponse.status}`);
            }
            const profileData = await profileResponse.json();
            setProfileData(profileData);

            const educationResponse = await fetch(`${process.env.REACT_APP_BACK_END_URL}/consultantprofile/1/education`);
            if (!educationResponse.ok) {
                throw new Error(`HTTP error! Status: ${educationResponse.status}`);
            }
            const educationData = await educationResponse.json();
            setEducationData(educationData);

            const careerResponse = await fetch(`${process.env.REACT_APP_BACK_END_URL}/consultantprofile/1/career`);
            if (!careerResponse.ok) {
                throw new Error(`HTTP error! Status: ${careerResponse.status}`);
            }
            const careerData = await careerResponse.json();
            setCareerData(careerData);

        } catch (error) {
            console.error('Error fetching profile data:', error);
        }
    };

    const handleEditClick = () => {
        if (profileData) {
            setEditedProfile(profileData);
            setIsModalOpen(true);
        }
    };

    const handleCloseModal = () => {
        setIsModalOpen(false);
        // 모달이 닫힐 때 비밀번호 필드 초기화
        setPassword('');
        setConfirmPassword('');
        setShowConfirmPassword(false);
    };

    const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
        const { name, value } = e.target;
        setEditedProfile(prev => prev ? { ...prev, [name]: value } : null);
    };

    const handlePasswordChange = (e: React.ChangeEvent<HTMLInputElement>) => {
        const { name, value } = e.target;
        if (name === 'password') {
            setPassword(value);
            setShowConfirmPassword(value.length > 0); // 비밀번호가 입력되면 비밀번호 확인 필드를 보여줍니다.
        } else if (name === 'confirmPassword') {
            setConfirmPassword(value);
        }
    };

    const handleSubmit = async (e: React.FormEvent) => {
        e.preventDefault();
        if (editedProfile) {
            if (password !== confirmPassword) {
                alert('비밀번호와 비밀번호 확인이 일치하지 않습니다.');
                return;
            }

            try {
                const response = await fetch(`${process.env.REACT_APP_BACK_END_URL}/consultantprofile/1/profile`, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({ ...editedProfile, password }), // 비밀번호 추가
                });
                if (!response.ok) {
                    throw new Error(`HTTP error! Status: ${response.status}`);
                }
                const updatedProfile = await response.json();
                setProfileData(updatedProfile);
                setIsModalOpen(false);
            } catch (error) {
                console.error('Error updating profile:', error);
            }
        }
    };

    if (!profileData) {
        return <div>Loading...</div>;
    }

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
                                    value={editedProfile?.name || ''}
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
                                    value={editedProfile?.title || ''}
                                    onChange={handleInputChange}
                                    disabled
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="password">🔒 새 비밀번호</Label>
                                <Input
                                    type="password"
                                    id="password"
                                    name="password"
                                    value={password}
                                    onChange={handlePasswordChange}
                                />
                            </FormGroup>
                            {showConfirmPassword && (
                                <FormGroup>
                                    <Label htmlFor="confirmPassword">🔒 비밀번호 확인</Label>
                                    <Input
                                        type="password"
                                        id="confirmPassword"
                                        name="confirmPassword"
                                        value={confirmPassword}
                                        onChange={handlePasswordChange}
                                    />
                                </FormGroup>
                            )}
                            <FormGroup>
                                <Label htmlFor="phone">📞 전화번호</Label>
                                <Input
                                    type="tel"
                                    id="phone"
                                    name="phone"
                                    value={editedProfile?.phone || ''}
                                    onChange={handleInputChange}
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="email">✉️ 이메일</Label>
                                <Input
                                    type="email"
                                    id="email"
                                    name="email"
                                    value={editedProfile?.email || ''}
                                    onChange={handleInputChange}
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="birthYear">🎂 생년월일</Label>
                                <Input
                                    type="text"
                                    id="birthYear"
                                    name="birthYear"
                                    value={editedProfile?.birthYear || ''}
                                    onChange={handleInputChange}
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="area">📍 활동 지역</Label>
                                <Input
                                    type="text"
                                    name="area"
                                    id="area"
                                    value={editedProfile?.area || ''}
                                    onChange={handleInputChange}
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="expertise">🌟 전문 분야</Label>
                                <Input
                                    type="text"
                                    id="expertise"
                                    name="expertise"
                                    value={editedProfile?.expertise || ''}
                                    onChange={handleInputChange}
                                    disabled
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="experience">💼 컨설팅 경력</Label>
                                <Input
                                    type="text"
                                    id="experience"
                                    name="experience"
                                    value={editedProfile?.experience || ''}
                                    onChange={handleInputChange}
                                    disabled
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="clients">👥 주요 클라이언트</Label>
                                <Input
                                    type="text"
                                    id="clients"
                                    name="clients"
                                    value={editedProfile?.clients || ''}
                                    onChange={handleInputChange}
                                    disabled
                                />
                            </FormGroup>
                            <FormGroup>
                                <Label htmlFor="introduce">📖 자기소개</Label>
                                <Textarea
                                    id="introduce"
                                    name="introduce"
                                    value={editedProfile?.introduce || ''}
                                    onChange={handleInputChange}
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