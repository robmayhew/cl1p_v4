'use client';

import React, { useEffect, useState } from 'react';
import { notFound } from "next/navigation";

export default function WildcardPage({ params }: { params: { slug?: string } }) {
  const { slug } = React.use(params);
  const [wildcardPath, setWildcardPath] = useState<{ path: string; } | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    if (!slug) {
      setLoading(false);
      return;
    }

    const joinSlug = slug.join("/");  

    fetch(`/api/wildcard/${joinSlug}`)
      .then((res) => {
        if (!res.ok) throw new Error('User not found');
        return res.json();
      })
      .then((data) => setWildcardPath(data))
      .catch(() => setWildcardPath(null))
      .finally(() => setLoading(false));
  }, [slug]);

  if (!slug) return notFound();
  if (loading) return <p>Loading...</p>;
  if (!wildcardPath) return <p>Wildcard path not found not found.</p>;

  return (
    <div className="flex flex-col items-center justify-center h-screen">
      <h1 className="text-4xl font-bold">Wildcard path</h1>
      <p className="text-lg mt-4">Path: <strong>{wildcardPath.path}</strong></p>
      
    </div>
  );
}
