package com.robmayhew.cl1p.control.message;

import com.robmayhew.cl1p.util.Cl1pStringUtil;



public class Path
{

    private final String path;

    public Path(Path p)
    {
        path = p.path;
    }

    public Path(String path)
    {
        String x = Cl1pStringUtil.removeTrailing(path, "/");
        if (!x.startsWith("/"))
            throw new RuntimeException("Path " + path + " is invalid");
        int max = 252 / 2;
        if (x.length() > max)
            x = x.substring(0, max);
        this.path = x;
    }

    public String getPath()
    {
        return path;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Path)) return false;

        Path path1 = (Path) o;

        return getPath() != null ? getPath().equals(path1.getPath()) : path1.getPath() == null;
    }

    @Override
    public int hashCode()
    {
        return getPath() != null ? getPath().hashCode() : 0;
    }

    @Override
    public String toString()
    {
        return "Path{" +
                "path='" + path + '\'' +
                '}';
    }
}
