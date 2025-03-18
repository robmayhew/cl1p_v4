import { useState } from "react";
import type { WildcardDTO } from "@/dto/wildcard-dto";
import type { GlobalSettingsDTO } from "@/dto/global-seetings";

type PageProps = {
    data: WildcardDTO;
    settings: GlobalSettingsDTO;
    onSubmitSuccess: (data:WildcardDTO) => void;
  };

export default function WildcardEditor({data, settings, onSubmitSuccess}: PageProps) {
  const [selectedOption, setSelectedOption] = useState("Option 1");
  const [text, setText] = useState(data.content);

  const handleSubmit = async () => {
    try {
      const response = await fetch("/api/wildcard/" + data.uri, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          content: text,
          expiration: selectedOption,
        }),
      });

      if (!response.ok) {
        throw new Error(`Error: ${response.statusText}`);
      }

      const result = await response.json();
      console.log("Success:", result);

       const savedData: WildcardDTO = {
            uri: "test",
            content:text,
            empty: false,
            rgdm: 'yup',
            blah: true
          };
      
      onSubmitSuccess(savedData);
    } catch (error) {
      console.error("Error submitting:", error);
    }
  };

  return (
    <div className="flex flex-col h-screen">
      {/* Status Bar */}
      <div className="flex items-center justify-between p-4 bg-gray-800 text-white">
        <h1 className="text-lg font-bold">{settings.siteName}/{data.uri}</h1>
        <div className="flex items-center space-x-4">
          <span className="text-sm">User: JohnDoe</span>
          <span className="bg-green-500 text-white px-2 py-1 rounded text-xs">Active</span>
        </div>
      </div>

      {/* Red Line */}
      <div className="h-1 bg-red-500 w-full"></div>

      {/* Instruction Line */}
      <p className="p-4 text-center text-gray-700">Paste in anything you want.</p>

      {/* Toolbar */}
      <div className="flex items-center space-x-4 p-4 bg-gray-200">
        <button className="bg-blue-500 text-white px-4 py-2 rounded"
         onClick={handleSubmit}
        >Create</button>
        <select
          className="p-2 border rounded"
          value={selectedOption}
          onChange={(e) => setSelectedOption(e.target.value)}
        >
 {settings.expirationOptions.map((option) => (
            <option key={option.value} value={option.value}>
              {option.label}
            </option>
          ))}
        </select>
      </div>

      {/* Text Area (Expands to Fill Remaining Space) */}
      <textarea
        className="flex-1 p-4 border border-gray-300 w-full resize-none"
        placeholder="Type here..."
        value={text}
        onChange={(e) => setText(e.target.value)}
      />
    </div>
  );
}
