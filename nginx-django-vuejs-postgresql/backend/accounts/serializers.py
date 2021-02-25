from django.contrib.auth import get_user_model
from rest_framework import serializers
User = get_user_model()

class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        # fields = ('id', 'title', 'check')
        fields = ('username',)


class UserCreateSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        # fields = ('id', 'title', 'check')
        fields = '__all__'


class UserUpdateSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        # fields = ('id', 'title', 'check')
        fields = '__all__'
