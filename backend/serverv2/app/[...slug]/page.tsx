'use client';

import React, { useEffect, useState } from 'react';
import { notFound } from "next/navigation";
// Import WildcardModel
import { WildcardDTO } from '@/dto/wildcard-dto';
import {GlobalSettingsDTO} from '@/dto/global-seetings';
import WildcardEditor from './editor';

export default function WildcardPage({ params }: { params: { slug?: string } }) {
  const { slug } = React.use(params);
  const [wildcardPath, setWildcardPath] = useState<WildcardDTO | null>(null); // Update state type
  const [globalSettings, setGlobalSettings] = useState<GlobalSettingsDTO | null>(null);
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
      .then((data) => {
        console.log(data);
        const model: WildcardDTO = data; // cool! 
        setWildcardPath(model);
      })
      .catch(() => setWildcardPath(null))
      .finally(() => setLoading(false));

      fetch('/api/globalsettings')
      .then((res) => {
        if (!res.ok) throw new Error('Global settings not found');
        return res.json();
      })
      .then((data) => {
        console.log(data);
        const model: GlobalSettingsDTO = data; // cool!
        setGlobalSettings(model);
      })
      .catch(() => setGlobalSettings(null))
      .finally(() => setLoading(false));

  }, [slug]);







  if (!slug) return notFound();
  if (loading) return <p>Loading...</p>;
  if (!wildcardPath) return <p>Wildcard path not found.</p>;
  if (!globalSettings) return <p>Global settings not found.</p>;

  return (
    <div>
      <WildcardEditor data={wildcardPath} settings={globalSettings}></WildcardEditor>
    </div>
  );
}
