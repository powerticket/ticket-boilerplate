from django.shortcuts import get_object_or_404
from rest_framework import status
from rest_framework.decorators import api_view
from rest_framework.response import Response
from django.contrib.auth import get_user_model
from .serializers import UserSerializer

@api_view(['GET'])
def index(request):
    User = get_user_model()
    users = User.objects.all()
    serializer = UserSerializer(users, many=True)
    return Response(serializer.data)


@api_view(['POST'])
def logout(request):
    pass


@api_view(['POST'])
def signup(request):
    pass


@api_view(['POST'])
def signout(request):
    pass
