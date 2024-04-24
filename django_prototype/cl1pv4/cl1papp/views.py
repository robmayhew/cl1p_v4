from django.shortcuts import render

# Create your views here.
def wildcard_view(request, path):
    return render(request, 'cl1papp/cl1p_found.html', {'path': path})