from django.urls import path, re_path
from . import views


urlpatterns = [
     # Capturing wildcard URL
    re_path(r'^(?P<path>.+)/$', views.wildcard_view, name='wildcard_view'),
]