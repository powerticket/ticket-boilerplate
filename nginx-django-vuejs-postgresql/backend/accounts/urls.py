from django.urls import path
from rest_framework_simplejwt.views import (TokenObtainPairView, TokenRefreshView, TokenVerifyView)
# from .views import MyTokenObtainPairView
from . import views

app_name = 'accounts'
urlpatterns = [
    # jwt auth
    path('login/', TokenObtainPairView.as_view(), name='login'),
    path('login/refresh/', TokenRefreshView.as_view(), name='login_refresh'),
    path('login/verify/', TokenVerifyView.as_view(), name='login_verify'),

    path('logout/', views.logout, name='logout'),
    
    path('', views.index),
    # signup
    path('signup/', views.signup, name='signup'),
    path('signout/', views.signout, name='signup'),
]
