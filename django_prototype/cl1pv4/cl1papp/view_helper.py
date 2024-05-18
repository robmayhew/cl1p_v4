from cl1papp.forms import TextContentForm
from cl1papp.models import RMPathKey
from cl1papp.models import RMPathContent

def fetch_text_content_for_path(path):
    try:    
        key = RMPathKey.objects.get(path=path);
        path_content = RMPathContent.objects.filter(key=key).order_by('-id').first()        
        return path_content.content
    except RMPathKey.DoesNotExist:
        return None
    except RMPathContent.DoesNotExist:
        return None
    
def save_text_content_for_path(path, text_content):
    rm_path_key = RMPathKey(path=path)
    try:
        rm_path_key = RMPathKey.objects.get(path=path)
    except RMPathKey.DoesNotExist:
        rm_path_key.save()
    RMPathContent.objects.create(key=rm_path_key, content=text_content)
