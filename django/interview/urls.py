from django.urls import path

from interview import views

urlpatterns=[
    path('face_detect', views.face_detect),
    path('speach_text', views.speach_text),
]