from django.urls import path, re_path
from . import views


urlpatterns = [
    path('', views.home, name='home'),
     # Capturing wildcard URL
    re_path(r'^(?P<path>.+)/$', views.wildcard_view, name='wildcard_view'),
]