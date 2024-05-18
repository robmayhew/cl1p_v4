from cl1papp.models import RMPathKey
from cl1papp.models import RMPathContent

def is_there_any_content_to_view(path):
    if RMPathKey.objects.filter(path=path).exists():
        if RMPathContent.objects.filter(key=RMPathKey.objects.get(path=path)).exists():
            return True
    return False

def is_allowed_to_read(path):
    # Always true for now
    return True

def is_allowed_to_write(path):
    # Always true for now
    return True


